package dev1.alexkjam64.SpringBootProject.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public record ClientName(int id, String firstName, String lastName, String middleInit){
    public MapSqlParameterSource mapInfo(int id){
        return new MapSqlParameterSource()
            .addValue("ID", id)
            .addValue("FIRST", firstName)
            .addValue("LAST", lastName)
            .addValue("MIDDLE", middleInit);
    }
}