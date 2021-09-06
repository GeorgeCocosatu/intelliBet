package com.intellibet.repository;

import com.intellibet.model.Bet;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BetRepository extends JpaRepository<Bet, Long> {

  @Query("select b from Bet b where b.user.email = :userEmail")
  List<Bet> findAllBetsByUserEmail(@Param("userEmail") String userEmail);


  @Query("select b from Bet b where b.user.email = :userEmail and b.event.outcome is NULL")
  List<Bet> findPendingBetsByUserEmail(@Param("userEmail") String userEmail);

  @Query("select b from Bet b where b.user.email = :userEmail and b.event.outcome is NOT NULL")
  List<Bet> findDecidedBetsByUserEmail(@Param("userEmail") String userEmail);
}
