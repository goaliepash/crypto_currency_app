package ru.pavelivanov.develop.cryptocurrency_app.models.pojo;

import com.google.gson.annotations.SerializedName;

public class Status {

    /**
     * Текущая временная метка (ISO 8601) на сервере.
     */
    @SerializedName("timestamp")
    public String timestamp;

    /**
     * Внутренний код ошибки для текущей ошибки.
     * Если уникальный код ошибки платформы недоступен, возвращается код состояния HTTP.
     * null возвращается, если ошибки нет.
     */
    @SerializedName("error_code")
    public Integer errorCode;

    /**
     * Сообщение об ошибке вместе с кодом ошибки.
     */
    @SerializedName("error_message")
    public String errorMessage;

    /**
     * Количество миллисекунд, использованных для генерации этого ответа.
     */
    @SerializedName("elapsed")
    public Integer elapsed;

    /**
     * Количество кредитов API, которые использовались для этого вызова.
     */
    @SerializedName("credit_count")
    public Integer creditCount;
}
