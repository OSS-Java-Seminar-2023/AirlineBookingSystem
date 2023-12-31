package hr.OSSAirline.mappers;

import hr.OSSAirline.dto.PurchaseDto;
import hr.OSSAirline.models.Purchase;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PurchaseMapper {
    PurchaseMapper INSTANCE = Mappers.getMapper(PurchaseMapper.class);

    @Mapping(source = "user", target = "user")
    @Mapping(source = "tickets", target = "tickets")
    @Mapping(source = "paymentInfo", target = "paymentInfo")
    @Mapping(source = "paymentDate", target = "paymentDate")
    PurchaseDto toDto(Purchase entity);

    @Mapping(source = "user", target = "user")
    @Mapping(source = "tickets", target = "tickets")
    @Mapping(source = "paymentInfo", target = "paymentInfo")
    @Mapping(source = "paymentDate", target = "paymentDate")
    Purchase toEntity(PurchaseDto dto);
}