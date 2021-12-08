package nl.hsleiden.svdj8.repository;

import nl.hsleiden.svdj8.models.tables.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
