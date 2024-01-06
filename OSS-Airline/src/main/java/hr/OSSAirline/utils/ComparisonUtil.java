package hr.OSSAirline.utils;

import hr.OSSAirline.dto.PassengerDto;

import java.util.Objects;

public class ComparisonUtil {

    public static boolean areEqual(PassengerDto dto, PassengerDto entity) {
        if (dto == entity) {
            return true;
        }
        if (dto == null || entity == null) {
            return false;
        }

        return Objects.equals(dto.getName(), entity.getName()) &&
                Objects.equals(dto.getSurname(), entity.getSurname()) &&
                Objects.equals(dto.getMail(), entity.getMail()) &&
                Objects.equals(dto.getPIN(), entity.getPIN()) &&
                Objects.equals(dto.getDOB(), entity.getDOB());
    }
}
