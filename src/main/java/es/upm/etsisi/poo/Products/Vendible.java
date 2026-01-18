package es.upm.etsisi.poo.Products;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.time.Duration;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ProductBasic.class, name = "basic"),
        @JsonSubTypes.Type(value = ProductPers.class, name = "personalized"),
        @JsonSubTypes.Type(value = Service.class, name = "service")
})

public abstract class Vendible {
    private String id;

    public Vendible() {}
    public Vendible(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public abstract String toString();

    public abstract int amountTicket(int amount);
    public boolean isPersonalizable(){
        return false;
    }
    public abstract Product copyProduct();
    public abstract Duration getMinTime();
}
