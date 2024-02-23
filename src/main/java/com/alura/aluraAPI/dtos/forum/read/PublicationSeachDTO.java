package com.alura.aluraAPI.dtos.forum.read;

import com.alura.aluraAPI.models.forum.PublicationStatus;

public record PublicationSeachDTO(String title, PublicationStatus status) {
}
