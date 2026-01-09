package app.Homi.HomiApp.dto;

public record apiResponseGeneric<T>(
        Boolean sucess,
        String message,
        T data
) {
}
