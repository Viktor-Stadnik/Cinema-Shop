package cinema.shop.service;

import cinema.shop.model.CinemaHall;
import java.util.List;

public interface CinemaHallService {
    CinemaHall save(CinemaHall cinemaHall);

    CinemaHall get(Long id);

    List<CinemaHall> getAll();
}
