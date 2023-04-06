package DAO;

import Domain.Buy;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;

import java.time.LocalDate;
import java.util.List;

public interface Buy_DAO {

    @SqlQuery("SELECT * FROM buys")
    @UseRowMapper(BuyMapper.class)
    List<Buy> getBuy();

    @SqlUpdate("INSERT INTO buys (id_client, buy_date) VALUES (?, ?)")
    void addBuy(int id_client, LocalDate date);

    @SqlUpdate("DELETE FROM buys WHERE id_buy = ?")
    void deleteBuy(int id_buy);


    @SqlQuery("SELECT * FROM buys WHERE id_client = ?")
    @UseRowMapper(BuyMapper.class)
    List<Buy> searchClientBuy(int id_client);




}