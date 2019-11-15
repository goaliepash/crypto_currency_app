package ru.pavelivanov.develop.cryptocurrency_app.remote.pojo;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.List;

public class Datum {

    /**
     * Уникальный идентификатор для криптовалюты.
     */
    @SerializedName("id")
    public Integer id;

    /**
     * Название криптовалюты.
     */
    @SerializedName("name")
    public String name;

    /**
     * Тикер для криптовалюты.
     */
    @SerializedName("symbol")
    public String symbol;

    /**
     * URL-версия сокращенного варианта имени криптовалюты.
     */
    @SerializedName("slug")
    public String slug;

    /**
     * Ранг CoinMarketCap криптовалюты по рыночной капитализации.
     */
    @SerializedName("cmc_rank")
    public Integer cmcRank;

    /**
     * Количество активных торговых пар, доступных для этой криптовалюты на поддерживаемых биржах.
     */
    @SerializedName("num_market_pairs")
    public Integer numMarketPairs;

    /**
     * Примерное количество монет, обращающихся за эту криптовалюту.
     */
    @SerializedName("circulating_supply")
    public BigDecimal circulatingSupply;

    /**
     * Примерное общее количество монет, существующих в настоящий момент
     * (за вычетом любых монет, которые точно сгорели).
     */
    @SerializedName("total_supply")
    public BigDecimal totalSupply;

    /**
     * Рыночная капитализация по общему предложению.
     */
    @SerializedName("market_cap_by_total_supply")
    public BigDecimal marketCapByTotalSupply;

    /**
     * Ожидаемый максимальный лимит монет, когда-либо доступных для этой криптовалюты.
     */
    @SerializedName("max_supply")
    public BigDecimal maxSupply;

    /**
     * Метка времени (ISO 8601) последнего обновления рыночных данных этой криптовалюты.
     */
    @SerializedName("last_updated")
    public String lastUpdated;

    /**
     * Отметка времени (ISO 8601), когда эта криптовалюта была добавлена в CoinMarketCap.
     */
    @SerializedName("date_added")
    public String dateAdded;

    /**
     * Массив тегов, связанных с этой криптовалютой.
     */
    @SerializedName("tags")
    public List<String> tags;

    /**
     * Метаданные о родительской платформе криптовалюты,
     * к которой принадлежит эта криптовалюта, если это токен,
     * в противном случае - ноль.
     */
    @SerializedName("platform")
    public Platform platform;

    /**
     * Рыночные котировки разных валют
     */
    @SerializedName("quote")
    public Quote quote;
}
