package com.adrian.mobsters.repository;

import com.adrian.mobsters.domain.ActionJob;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.BitSet;
import java.util.List;

public interface ActionJobRepository extends MongoRepository<ActionJob, String> {
    List<ActionJob> findAllByStatus(String status);
}
