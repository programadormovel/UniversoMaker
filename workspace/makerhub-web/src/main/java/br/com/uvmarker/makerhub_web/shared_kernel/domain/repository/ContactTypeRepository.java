package br.com.uvmarker.makerhub_web.shared_kernel.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.uvmarker.makerhub_web.shared_kernel.domain.entity.ContactType;

@Repository
public interface ContactTypeRepository extends JpaRepository<ContactType, Long> {

    List<ContactType> findByIsActiveTrue();
}