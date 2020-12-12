package com.github.training.database;

import org.springframework.data.repository.CrudRepository;
import java.util.UUID;

public interface ShippingInformationRepository extends CrudRepository<ShippingInformation, UUID> {

}
