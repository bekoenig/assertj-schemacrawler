# AssertJ SchemaCrawler - Fluent Assertions for SchemaCrawler

## Motivation
Writing junit tests with [SchemaCrawler](https://github.com/schemacrawler/) is powerful to check the consistency of 
a data model change executed using some database migrator like flyway or
liquibase. The catalog api is well designed but also very complex. So it's hard
to getting in and the resulting assertion code has a lot of boilerplate and
looks like a mess:

```
Table table = catalog.lookupTable(
  catalog.lookupSchema("EXAMPLE_SCHEMA").orElseThrow(),
  "EXAMPLE_ENTITY1").orElseThrow();

assertThat(table.lookupColumn("ENTITY1_ID")).get()
  .satisfies(x -> {
    assertThat(x.getColumnDataType().getName()).isEqualTo("CHAR");
    assertThat(x.getSize()).isEqualTo(36);
  });

assertThat(table.lookupColumn("ATTRIBUTE1")).get()
  .satisfies(x -> {
    assertThat(x.getColumnDataType().getName()).isEqualTo("VARCHAR");
    assertThat(x.getSize()).isEqualTo(250);
  });

assertThat(table.lookupTableConstraint("EXAMPLE_ENTITY1_PK"))
  .get()
  .extracting(TableConstraint::getConstrainedColumns)
  .asInstanceOf(InstanceOfAssertFactories.list(TableConstraint.class))
  .anyMatch(x -> x.getName().equals("ENTITY1_ID"));

```

Using assertj-schemacrawler the code reduces to:

```
TableAssert table = SchemaCrawlerAssertions.assertThat(catalog)
  .table("EXAMPLE_SCHEMA", "EXAMPLE_ENTITY1");

table.column("ENTITY1_ID")
  .matchesColumnDataTypeName(Predicate.isEqual("CHAR"))
  .matchesSize(Predicate.isEqual(36));

table.column("ATTRIBUTE1")
  .matchesColumnDataTypeName(Predicate.isEqual("VARCHAR"))
  .matchesSize(Predicate.isEqual(250));

table.tableConstraint("EXAMPLE_ENTITY1_PK")
  .constrainedColumns()
  .anyMatch(x -> x.getName().equals("ENTITY1_ID"));
```

## Getting Started

Include 

```
<dependency>
  <groupId>io.github.bekoenig</groupId>
  <artifactId>assertj-schemacrawler</artifactId>
  <version>*current version*</version>
  <scope>test</scope>
</dependency>
```

in your project.

The class `SchemaCrawlerAssertions` is the entry point and contains `assertThat` 
overloads for all schema crawler types.

```
Catalog catalog = ...;

SchemaCrawlerAssertions
  .assertThat(catalog)
  .table("EXAMPLE_SCHEMA", "EXAMPLE_ENTITY1").isNotNull()
```

The class `SchemaCrawlerInstanceOfAssertFactories` contains factory methods
to provide implementations for `InstanceOfAssertFactory`. This is useful to
produce a new assert narrowed to that type using `asInstanceOf`. 

```
columnAssert.parent()
  .asInstanceOf(SchemaCrawlerInstanceOfAssertFactories.table())
  .column("ATTRIBUTE1");
```

## Conventions

Each assert class is derivated from comparable or object assert and contains an
assert method for each api method of the associated schema crawler type.

The assert methods follow this naming pattern: 

| method of SchemaCrawler type         | assertion method                                                           |
|--------------------------------------|----------------------------------------------------------------------------|
| *primitive type* getSomething()      | matchesSomething(Predicate< *primitive type* >)                            |
| *complex type* getSomething()        | satisfiesSomething(Consumer< *complex type* >)                             |
| boolean is- or hasSomething()        | is- or hasSomething(boolean)                                               |  
| *list or collection* getSomethings() | *specific assert for list or collection with element support* somethings() |
| *optional* lookupSomething(String)   | *specific assert for something with nullable on empty* something(String)   |
| *complex type* getParent()           | *object assert for complex type* parent()                                  |

## Compatibility 

Even if this library is a standalone project (see [SchemaCrawler Issue 1544](https://github.com/schemacrawler/SchemaCrawler/issues/1544)) the goal is to
provide a maximum compatibility to the latest version of schema crawler using
state of the art coding style of assertj.