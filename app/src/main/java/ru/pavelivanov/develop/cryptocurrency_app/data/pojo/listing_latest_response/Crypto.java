package ru.pavelivanov.develop.cryptocurrency_app.data.pojo.listing_latest_response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.List;

/**
 * Parcelable-класс для получения объектов криптовалюты из интернета
 *
 * @author Иванов Павел Александрович
 */
public class Crypto implements Parcelable {

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

    /**
     * Класс по умолчанию.
     */
    public Crypto() {

    }

    protected Crypto(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        name = in.readString();
        symbol = in.readString();
        slug = in.readString();
        if (in.readByte() == 0) {
            cmcRank = null;
        } else {
            cmcRank = in.readInt();
        }
        if (in.readByte() == 0) {
            numMarketPairs = null;
        } else {
            numMarketPairs = in.readInt();
        }
        lastUpdated = in.readString();
        dateAdded = in.readString();
        tags = in.createStringArrayList();
    }

    public static final Creator<Crypto> CREATOR = new Creator<Crypto>() {
        @Override
        public Crypto createFromParcel(Parcel in) {
            return new Crypto(in);
        }

        @Override
        public Crypto[] newArray(int size) {
            return new Crypto[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        parcel.writeString(name);
        parcel.writeString(symbol);
        parcel.writeString(slug);
        if (cmcRank == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(cmcRank);
        }
        if (numMarketPairs == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(numMarketPairs);
        }
        parcel.writeString(lastUpdated);
        parcel.writeString(dateAdded);
        parcel.writeStringList(tags);
    }
}
