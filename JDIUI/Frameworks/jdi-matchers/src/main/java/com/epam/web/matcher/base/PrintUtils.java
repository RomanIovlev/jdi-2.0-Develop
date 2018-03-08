package com.epam.web.matcher.base;

/**
 * Created by Roman Iovlev on 14.02.2018
 * Email: roman.iovlev.jdi@gmail.com; Skype: roman.iovlev
 */

import com.epam.jdi.tools.map.MapArray;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.epam.jdi.tools.PrintUtils.print;
import static com.epam.jdi.tools.ReflectionUtils.*;
import static java.lang.Integer.parseInt;
import static java.lang.reflect.Array.get;
import static java.lang.reflect.Array.getLength;

public final class PrintUtils {

    private PrintUtils() {
    }

    private static String printObject(Object obj) {
        List<String> result = new ArrayList<>();
        for (Field field : getFields(obj, Object.class)) {
            Object value = getValueField(field, obj);
            String strValue = null;
            if (value == null)
                strValue = "#NULL#";
            else if (isClass(value.getClass(), String.class))
                strValue = (String) value;
            else if (isClass(value.getClass(), Enum.class))
                strValue = value.toString();
            else if (field.isAnnotationPresent(Complex.class))
                strValue = "#(#" + printObject(value) + "#)#";
            if (strValue != null)
                result.add(String.format("%s#:#%s", field.getClass().getSimpleName(), strValue));
        }
        return print(result, "#;#", "%s");
    }

    public static MapArray<String, String> objToSetValue(Object obj) {
        return (obj == null)
                ? new MapArray<>()
                : parseObjectAsString(printObject(obj));
    }

    public static String processValue(String input, List<String> values) {
        if (input.equals("#NULL#"))
            return null;
        if (input.matches("#VAL\\d*"))
            return values.get(parseInt(input.substring(4)) - 1);
        return input;
    }

    public static MapArray<String, String> parseObjectAsString(String string) {
        if (string == null)
            return null;
        MapArray<String, String> result = new MapArray<>();
        List<String> values = new ArrayList<>();
        int i = 1;
        String str = string;
        while (string.indexOf("#(#") > 0) {
            values.add(string.substring(string.indexOf("#(#") + 3, string.indexOf("#)#")));
            str = string.replaceAll("#\\(#.*#\\)#", "#VAL" + i++);
        }
        String[] fields = str.split("#;#");
        for (String field : fields) {
            String[] splitField = field.split("#:#");
            if (splitField.length == 2)
                result.add(splitField[0], processValue(splitField[1], values));
        }
        return result;
    }

    public static String printObjectAsArray(Object array) {
        List<String> elements = new ArrayList<>();
        for (int i = 0; i <= getLength(array); i++)
            elements.add(get(array, i).toString());
        return print(elements);
    }
}
