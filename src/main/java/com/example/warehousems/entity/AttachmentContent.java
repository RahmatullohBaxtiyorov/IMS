package com.example.warehousems.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AttachmentContent {
    @Id
    @GeneratedValue
    private UUID id;
    private byte[] bytes;
    @OneToOne
    private Attachment attachment;
}
