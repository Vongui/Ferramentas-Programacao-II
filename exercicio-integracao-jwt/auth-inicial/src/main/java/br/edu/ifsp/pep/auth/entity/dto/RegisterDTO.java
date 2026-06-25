package br.edu.ifsp.pep.auth.entity.dto;

import br.edu.ifsp.pep.auth.entity.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
