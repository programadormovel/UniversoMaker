package br.com.uvmarker.makerhub_web.shared_kernel.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.uvmarker.makerhub_web.shared_kernel.domain.entity.MessageToAdmin;
import br.com.uvmarker.makerhub_web.shared_kernel.domain.repository.MessageToAdminRepository;
import br.com.uvmarker.makerhub_web.shared_kernel.service.MessageToAdminService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageToAdminServiceImpl implements MessageToAdminService {

    private final MessageToAdminRepository messageToAdminRepository;

    @Override
    public List<MessageToAdmin> findAll() {
        return messageToAdminRepository.findAll();
    }

    @Override
    public List<MessageToAdmin> findAllActive() {
        return messageToAdminRepository.findByIsActiveTrue();
    }

    @Override
    public MessageToAdmin findById(Long id) {
        return messageToAdminRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Mensagem não encontrada com ID: " + id));
    }

    @Override
    public MessageToAdmin sendMessageToAdmin(MessageToAdmin messageToAdmin) {
        MessageToAdmin messageToAdminToSave = MessageToAdmin.builder()
                .sender(messageToAdmin.getSender())
                .email(messageToAdmin.getEmail())
                .subject(messageToAdmin.getSubject())
                .message(messageToAdmin.getMessage())
                .build();
        return messageToAdminRepository.save(messageToAdminToSave);
    }

    @Override
    public MessageToAdmin inactivateById(Long id) {
        @SuppressWarnings("null")
        MessageToAdmin messageToAdmin = messageToAdminRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Mensagem não encontrada com ID: " + id));
        messageToAdmin.setIsActive(false);
        return messageToAdminRepository.save(messageToAdmin);
    }

}
