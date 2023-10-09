package com.management.countrymanagement.domain;

import com.management.countrymanagement.domain.enums.ExchangeType;
import com.management.countrymanagement.domain.input.ModifyProductInput;
import com.management.countrymanagement.domain.input.NewProductInput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Optional;

@Document(collection = "products")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDocument {
    @Id
    @Field("_id")
    private ObjectId id;
    private String name;
    private String description;
    private BigDecimal unitPrice;
    private ExchangeType exchangeTypePredefined;
    private OffsetDateTime createdProductDate;
    private OffsetDateTime lastModificationDate;

    public ProductDocument(NewProductInput productInput){
        Optional.ofNullable(productInput.getName()).map(v -> this.name = v);
        Optional.ofNullable(productInput.getDescription()).map(v -> this.description = v);
        Optional.ofNullable(productInput.getUnitPrice()).map(v -> this.unitPrice = v);
        Optional.ofNullable(productInput.getExchangeTypePredefined()).map(v -> this.exchangeTypePredefined = ExchangeType.valueOf(v));
        this.createdProductDate = OffsetDateTime.now();
    }

    public ProductDocument(ModifyProductInput productInput){
        Optional.ofNullable(productInput.getName()).map(v -> this.name = v);
        Optional.ofNullable(productInput.getDescription()).map(v -> this.description = v);
        Optional.ofNullable(productInput.getUnitPrice()).map(v -> this.unitPrice = v);
        Optional.ofNullable(productInput.getExchangeTypePredefined()).map(v -> this.exchangeTypePredefined = ExchangeType.valueOf(v));
        this.lastModificationDate = OffsetDateTime.now();
    }
}
