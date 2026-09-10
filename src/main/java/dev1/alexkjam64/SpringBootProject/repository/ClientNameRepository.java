package dev1.alexkjam64.SpringBootProject.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

@Repository
public class ClientNameRepository {
    private final NamedParameterJdbcTemplate template;

    public ClientNameRepository(NamedParameterJdbcTemplate template){
        this.template = template;
    }

    private static final String batchQuery = """
            SELECT "id", "firstName", "lastName", "middleInit"
            FROM "Nintendo"."Name"
            WHERE "id" IN (:ID)
            """;

    public List<ClientName> getBatchNames(List<Integer> ids){
        return template.query(batchQuery, new MapSqlParameterSource("ID", ids), new ClientNameMapper());
    }

    private static final String query = """
            SELECT "id", "firstName", "lastName", "middleInit"
	        FROM "Nintendo"."Name"
            WHERE "id" = :ID
            """;

    public ClientName getName(int id){
        try{
            return template.queryForObject(query, new MapSqlParameterSource("ID", id), new ClientNameMapper());
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    private static final String insertQuery = """
            INSERT INTO "Nintendo"."Name" ("id", "firstName", "lastName", "middleInit")
            VALUES (:ID, :FIRST, :LAST, :MIDDLE)
            """;

    // Inserting new data into database
    public void addClient(ClientName newClient, int id){
        template.update(insertQuery, newClient.mapInfo(id));
    }

    // Updating data into database
    private static final String updateQuery = """
            UPDATE "Nintendo"."Name"
            SET "firstName" = :FIRST, "lastName" = :LAST, "middleInit" = :MIDDLE
            WHERE "id" = :ID
            """;

    public void updateClient(ClientName updateClient, int id){
        template.update(updateQuery, updateClient.mapInfo(id));
    }

    // Deleting data from database
    private static final String deleteQuery = """
            DELETE FROM "Nintendo"."Name"
            WHERE "id" = :ID
            """;

    public void deleteClient(int id){
        template.update(deleteQuery, new MapSqlParameterSource("ID", id));
    }
}
