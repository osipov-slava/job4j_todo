package ru.job4j.todo.util;

import ru.job4j.todo.model.Task;
import ru.job4j.todo.model.User;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

public class Utils {

    public static List<String> getZonesIds() {
        List<String> zoneIds = new ArrayList<>();
        Collections.addAll(zoneIds, TimeZone.getAvailableIDs());
        return zoneIds;
    }

    public static Task correctTimeZone(Task task, User user) {
        var timezone = user.getTimezone().isEmpty() ? TimeZone.getDefault().getID() : user.getTimezone();
        task.setCreated(task.getCreated()
                .atZone(ZoneId.of("UTC"))
                .withZoneSameInstant(ZoneId.of(timezone)).toLocalDateTime());
        return task;
    }

    public static List<Task> correctTimeZoneList(List<Task> tasks, User user) {
        tasks.forEach(task -> correctTimeZone(task, user));
        return tasks;
    }

}
