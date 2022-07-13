package challenge_1.converters;

import challenge_1.domain.PurchaseReceipt;
import challenge_1.dto.PurchaseReceiptDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PurchaseReceiptToDtoConverter implements Converter<PurchaseReceipt, PurchaseReceiptDto> {
    @Override
    public PurchaseReceiptDto convert(PurchaseReceipt source) {
        return new PurchaseReceiptDto(
                source.getProductId(),
                source.getTotal(),
                source.getChange()
        );
    }
}