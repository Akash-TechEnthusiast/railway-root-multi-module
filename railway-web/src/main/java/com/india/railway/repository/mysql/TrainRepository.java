package com.india.railway.repository.mysql;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.india.railway.model.mysql.Train;
import com.india.railway.model.mysql.TrainNameProjection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface TrainRepository extends JpaRepository<Train, Long> {

    @Query("SELECT t.train_name AS trainName,t.train_number AS trainNumber FROM Train t JOIN t.passengers p WHERE p.id = :passengerId")
    List<TrainNameProjection> findTrainsByPassengerId(@Param("passengerId") Long passengerId);

    @Query("SELECT t FROM Train t JOIN t.passengers p WHERE p.id = :passengerId")
    Page<Train> findTrainsListByPassengerId(@Param("passengerId") Long passengerId, Pageable pageable);

}