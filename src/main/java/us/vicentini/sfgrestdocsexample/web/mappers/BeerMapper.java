package us.vicentini.sfgrestdocsexample.web.mappers;

import org.mapstruct.Mapper;
import us.vicentini.sfgrestdocsexample.domain.Beer;
import us.vicentini.sfgrestdocsexample.web.model.BeerDto;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

    BeerDto beerToBeerDto(Beer beer);

    Beer beerDtoToBeer(BeerDto dto);
}
