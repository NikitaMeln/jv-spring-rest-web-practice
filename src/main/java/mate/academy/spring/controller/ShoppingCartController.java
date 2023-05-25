package mate.academy.spring.controller;

import mate.academy.spring.mapper.DtoResponseMapper;
import mate.academy.spring.model.ShoppingCart;
import mate.academy.spring.model.dto.response.ShoppingCartResponseDto;
import mate.academy.spring.service.MovieSessionService;
import mate.academy.spring.service.ShoppingCartService;
import mate.academy.spring.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {
    private final DtoResponseMapper<ShoppingCartResponseDto, ShoppingCart> responseMapper;
    private final UserService userService;
    private final MovieSessionService movieSessionService;
    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(DtoResponseMapper<ShoppingCartResponseDto,ShoppingCart>
                                          responseMapper,
                                  UserService userService,
                                  MovieSessionService movieSessionService,
                                  ShoppingCartService shoppingCartService) {
        this.responseMapper = responseMapper;
        this.userService = userService;
        this.movieSessionService = movieSessionService;
        this.shoppingCartService = shoppingCartService;
    }

    @PutMapping("/movie-sessions")
    public void add(@RequestParam Long movieSessionId, @RequestParam Long userId) {
        shoppingCartService.addSession(movieSessionService.get(movieSessionId),
                userService.get(userId));
    }

    @GetMapping("/by-user")
    public ShoppingCartResponseDto get(@RequestParam Long userId) {
        return responseMapper.toDto(shoppingCartService.getByUser(userService.get(userId)));
    }
}