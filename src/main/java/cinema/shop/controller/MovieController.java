package cinema.shop.controller;

import cinema.shop.dto.request.MovieRequestDto;
import cinema.shop.dto.response.MovieResponseDto;
import cinema.shop.model.Movie;
import cinema.shop.service.MovieService;
import cinema.shop.service.mapper.RequestDtoMapper;
import cinema.shop.service.mapper.ResponseDtoMapper;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final ResponseDtoMapper<MovieResponseDto, Movie> movieResponseDtoMapper;
    private final RequestDtoMapper<MovieRequestDto, Movie> movieRequestDtoMapper;

    public MovieController(MovieService movieService,
                           ResponseDtoMapper<MovieResponseDto, Movie> movieResponseDtoMapper,
                           RequestDtoMapper<MovieRequestDto, Movie> movieRequestDtoMapper) {
        this.movieService = movieService;
        this.movieResponseDtoMapper = movieResponseDtoMapper;
        this.movieRequestDtoMapper = movieRequestDtoMapper;
    }

    @PostMapping
    public MovieResponseDto save(@RequestBody @Valid MovieRequestDto requestDto) {
        Movie movie = movieService.save(
                movieRequestDtoMapper.mapToModel(requestDto));
        return movieResponseDtoMapper.mapToDto(movie);
    }

    @GetMapping
    public List<MovieResponseDto> getAll() {
        return movieService.getAll()
                .stream()
                .map(movieResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
