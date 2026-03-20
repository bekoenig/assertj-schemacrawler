package io.github.bekoenig.assertj.schemacrawler.api;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import schemacrawler.schema.*;
import schemacrawler.schemacrawler.SchemaReference;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiCompletenessTest {

    @TestFactory
    Stream<DynamicTest> testCompleteness() {
        Map<Class<?>, Class<?>> classMap = new LinkedHashMap<>();
        classMap.put(Catalog.class, CatalogAssert.class);
        classMap.put(Schema.class, SchemaAssert.class);
        classMap.put(Table.class, TableAssert.class);
        classMap.put(Column.class, ColumnAssert.class);
        classMap.put(Index.class, IndexAssert.class);
        classMap.put(ForeignKey.class, ForeignKeyAssert.class);
        classMap.put(PrimaryKey.class, PrimaryKeyAssert.class);
        classMap.put(Routine.class, RoutineAssert.class);
        classMap.put(Synonym.class, SynonymAssert.class);
        classMap.put(Sequence.class, SequenceAssert.class);
        classMap.put(Trigger.class, TriggerAssert.class);
        classMap.put(Privilege.class, PrivilegeAssert.class);
        classMap.put(Grant.class, GrantAssert.class);
        classMap.put(View.class, ViewAssert.class);
        classMap.put(ColumnDataType.class, ColumnDataTypeAssert.class);
        classMap.put(ColumnReference.class, ColumnReferenceAssert.class);
        classMap.put(IndexColumn.class, IndexColumnAssert.class);
        classMap.put(SchemaReference.class, SchemaReferenceAssert.class);
        classMap.put(TableConstraint.class, TableConstraintAssert.class);
        classMap.put(TableConstraintColumn.class, TableConstraintColumnAssert.class);
        classMap.put(TableReference.class, TableReferenceAssert.class);

        return classMap.entrySet().stream()
                .map(entry -> DynamicTest.dynamicTest(entry.getKey().getSimpleName(),
                        () -> assertCompleteness(entry.getKey(), entry.getValue())));
    }

    private void assertCompleteness(Class<?> scClass, Class<?> assertClass) {
        Set<String> assertMethods = getAllMethods(assertClass);
        List<String> missingMethods = new ArrayList<>();

        for (Method method : scClass.getMethods()) {
            if (shouldBeCovered(method)) {
                if (!isCovered(scClass, method, assertMethods)) {
                    missingMethods.add(method.getName() + "(" + Arrays.toString(method.getParameterTypes()) + ")");
                }
            }
        }

        assertThat(missingMethods).as("Missing methods in %s for %s", assertClass.getSimpleName(), scClass.getSimpleName()).isEmpty();
    }

    private boolean shouldBeCovered(Method method) {
        if (!Modifier.isPublic(method.getModifiers())) {
            return false;
        }
        String name = method.getName();
        if (name.equals("getClass") || name.equals("wait") || name.equals("notify") || name.equals("notifyAll") || name.equals("toString") || name.equals("equals") || name.equals("hashCode") || name.equals("compareTo") || name.equals("clone")) {
            return false;
        }
        // Exclude setters and other non-getter methods
        if (name.startsWith("set") || name.equals("reduce") || name.equals("undo") || name.startsWith("remove") || name.equals("add") || name.equals("withQuoting")) {
            return false;
        }
        // Exclude Iterable methods
        if (name.equals("iterator") || name.equals("spliterator") || name.equals("forEach")) {
            return false;
        }
        return true;
    }

    private boolean isCovered(Class<?> scClass, Method scMethod, Set<String> assertMethods) {
        String name = scMethod.getName();
        List<String> candidates = new ArrayList<>();
        candidates.add(name);

        if (name.startsWith("get") && name.length() > 3) {
            String base = name.substring(3);
            String decapitalized = Character.toLowerCase(base.charAt(0)) + base.substring(1);
            candidates.add(decapitalized);
            candidates.add("has" + base);
            candidates.add("is" + base);
            candidates.add("satisfies" + base);
            candidates.add("matches" + base);
        } else if (name.startsWith("is") && name.length() > 2) {
            String base = name.substring(2);
            String decapitalized = Character.toLowerCase(base.charAt(0)) + base.substring(1);
            candidates.add(decapitalized);
            candidates.add("is" + base);
            candidates.add("has" + base);
            candidates.add("satisfies" + base);
        } else if (name.startsWith("has") && name.length() > 3) {
            String base = name.substring(3);
            String decapitalized = Character.toLowerCase(base.charAt(0)) + base.substring(1);
            candidates.add(name);
            candidates.add(decapitalized);
            candidates.add("has" + base);
            candidates.add("satisfies" + base);
        } else if (name.startsWith("lookup") && name.length() > 6) {
            String base = name.substring(6);
            String decapitalized = Character.toLowerCase(base.charAt(0)) + base.substring(1);
            candidates.add(decapitalized);
        }

        // Special mappings
        if (name.equals("key")) {
            candidates.add("matchesKey");
        }
        if (scClass.equals(View.class) && name.equals("lookupTable")) {
            candidates.add("table");
        }
        if (scClass.equals(View.class) && name.equals("getCheckOption")) {
            candidates.add("satisfiesCheckOption");
        }
        if (scClass.equals(View.class) && name.equals("getTableUsage")) {
            candidates.add("tableUsage");
        }
        if (name.equals("getAttribute") || name.equals("lookupAttribute") || name.equals("getAttributes")) {
            candidates.add("attribute");
            candidates.add("hasAttribute");
        }
        if (name.equals("getRemarks") || name.equals("hasRemarks")) {
            candidates.add("remarks");
            candidates.add("matchesRemarks");
            candidates.add("hasRemarks");
        }
        if (name.equals("getName")) {
            candidates.add("hasName");
            candidates.add("matchesName");
        }
        if (name.equals("getFullName")) {
            candidates.add("hasFullName");
            candidates.add("matchesFullName");
        }
        if (name.equals("getPrimaryKeyTable")) {
            candidates.add("satisfiesPrimaryKeyTable");
        }
        if (name.equals("getForeignKeyTable")) {
            candidates.add("satisfiesForeignKeyTable");
        }
        if (name.equals("getDependentTable")) {
            candidates.add("satisfiesDependentTable");
        }
        if (name.equals("getReferencedTable")) {
            candidates.add("satisfiesReferencedTable");
        }
        if (name.equals("getReferencedObject")) {
            candidates.add("satisfiesReferencedObject");
        }
        if (name.equals("getParent")) {
            candidates.add("parent");
        }

        for (String candidate : candidates) {
            if (assertMethods.contains(candidate)) {
                return true;
            }
        }

        return false;
    }

    private Set<String> getAllMethods(Class<?> clazz) {
        Set<String> methods = new HashSet<>();
        Class<?> current = clazz;
        while (current != null && current != Object.class) {
            for (Method m : current.getDeclaredMethods()) {
                methods.add(m.getName());
            }
            current = current.getSuperclass();
        }
        return methods;
    }
}
