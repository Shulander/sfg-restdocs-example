package us.vicentini.sfgrestdocsexample.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.vicentini.sfgrestdocsexample.repositories.BeerRepository;
import us.vicentini.sfgrestdocsexample.web.mappers.BeerMapper;
import us.vicentini.sfgrestdocsexample.web.model.BeerDto;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping(BeerController.BASE_PATH)
@RestController
public class BeerController {
    public static final String BASE_PATH = "/api/v1/beer";
    private final BeerMapper beerMapper;
    private final BeerRepository beerRepository;


    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerId) {
        return beerRepository.findById(beerId)
                .map(beerMapper::beerToBeerDto)
                .map(beerDto -> new ResponseEntity<>(beerDto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping
    public ResponseEntity<Void> saveNewBeer(@RequestBody @Validated BeerDto beerDto) {

        beerRepository.save(beerMapper.beerDtoToBeer(beerDto));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PutMapping("/{beerId}")
    public ResponseEntity<Void> updateBeerById(@PathVariable("beerId") UUID beerId,
                                               @RequestBody @Validated BeerDto beerDto) {
        beerRepository.findById(beerId).ifPresent(beer -> {
            beer.setBeerName(beerDto.getBeerName());
            beer.setBeerStyle(beerDto.getBeerStyle().name());
            beer.setPrice(beerDto.getPrice());
            beer.setUpc(beerDto.getUpc());

            beerRepository.save(beer);
        });

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
