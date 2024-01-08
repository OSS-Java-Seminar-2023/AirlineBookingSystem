package hr.OSSAirline.services;

import hr.OSSAirline.dto.ReservationDto;
import hr.OSSAirline.mappers.ReservationMapper;
import hr.OSSAirline.models.Reservation;
import hr.OSSAirline.models.User;
import hr.OSSAirline.repositories.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {
    public final ReservationRepository reservationRepository;

    private final ReservationMapper reservationMapper;

    public List<ReservationDto> getAllUserReservations(User user){
        return reservationRepository.findReservationsByUser(user).stream()
                .map(reservationMapper::toDto)
                .toList();
    };

    public ReservationDto getReservationById(String id){
        return reservationMapper.toDto(reservationRepository.findReservationsById(id));
    }
}
