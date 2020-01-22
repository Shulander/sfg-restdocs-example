package us.vicentini.sfgrestdocsexample.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import us.vicentini.sfgrestdocsexample.domain.Beer;

import java.util.UUID;

public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {
}
