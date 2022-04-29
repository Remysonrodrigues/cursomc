package com.udemy.cursomc.services;

import com.udemy.cursomc.domain.Cliente;
import com.udemy.cursomc.repositories.ClienteRepository;
import com.udemy.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private Random random = new Random();

    @Autowired
    private EmailService emailService;

    public void sendNewPassword(String email) {

        Cliente cliente = clienteRepository.findByEmail(email);
        if (cliente == null) {
            throw new ObjectNotFoundException("Email não encontrado");
        }

        String newPassword = newPassword();
        cliente.setSenha(bCryptPasswordEncoder.encode(newPassword));

        clienteRepository.save(cliente);

        emailService.sendNewPasswordEmail(cliente, newPassword);

    }

    private String newPassword() {
        char[] vet = new char[10];
        for (int i = 0; i < vet.length; i++) {
            vet[i] = randomChar();
        }
        return new String(vet);
    }

    private char randomChar() {
        int opt = random.nextInt(3);
        if (opt == 0) { // gera um digito
            return (char) (random.nextInt(10) + 48);
        } else if (opt == 1) { // gera letra maiuscula
            return (char) (random.nextInt(26) + 65);
        } else { // gera letra minuscula
            return (char) (random.nextInt(10) + 97);
        }
    }

}
