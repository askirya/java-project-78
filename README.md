# Валидатор данных

[![Actions Status](https://github.com/askirya/java-project-78/actions/workflows/gradle.yml/badge.svg)](https://github.com/askirya/java-project-78/actions/workflows/gradle.yml)
[![SonarQube](https://sonarcloud.io/api/project_badges/measure?project=askirya_java-project-78&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=askirya_java-project-78)

## Описание

Валидатор данных — Java-проект, реализующий проверку значений по настраиваемым схемам.

Схемы:

- `StringSchema` — проверка строк: обязательность, минимальная длина, наличие подстроки.
- `NumberSchema` — проверка чисел: обязательность, положительное значение, диапазон.
- `MapSchema` — проверка объектов `Map`: обязательность, размер и вложенная валидация значений через `shape()`.

## Требования

- Java 21
- Gradle 8.7 или выше

## Проверка

```cmd
gradle --version
java --version
cd app
gradlew.bat check
```

## Пример использования

```java
import hexlet.code.Validator;

var validator = new Validator();
var schema = validator.string().required().minLength(5).contains("hex");

schema.isValid("hexlet"); // true
schema.isValid("java"); // false
```
