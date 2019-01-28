package nl.hu.tosad.webserver.target_database.data;

import nl.hu.tosad.domain.target_database.DbColumn;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColumnRepository extends CrudRepository<DbColumn, Long> {
    List<DbColumn> findAllByTableId(Long id);
}
