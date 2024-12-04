package enset.ali.transferservice.mappers;

import enset.ali.transferservice.DTOs.TransferDTO;
import enset.ali.transferservice.entities.Transfer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransferMapper {
    TransferDTO toTransferDTO(Transfer transfer);
    Transfer toTransfer(TransferDTO transferDTO);
}
