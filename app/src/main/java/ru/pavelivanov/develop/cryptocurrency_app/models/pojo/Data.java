package ru.pavelivanov.develop.cryptocurrency_app.models.pojo;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Data {

    /**
     * Список криптовалюты.
     */
    @SerializedName("data")
    public List<Datum> data;

    /**
     * Статус вызова API.
     */
    @SerializedName("status")
    public Status status;
}
