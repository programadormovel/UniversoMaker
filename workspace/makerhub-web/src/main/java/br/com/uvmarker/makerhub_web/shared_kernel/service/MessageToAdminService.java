package br.com.uvmarker.makerhub_web.shared_kernel.service;

import java.util.List;

import br.com.uvmarker.makerhub_web.shared_kernel.domain.entity.MessageToAdmin;

public interface MessageToAdminService {

    List<MessageToAdmin> findAll();

    List<MessageToAdmin> findAllActive();

    MessageToAdmin findById(Long id);

    MessageToAdmin sendMessageToAdmin(MessageToAdmin messageToAdmin);

    MessageToAdmin inactivateById(Long id);
}
