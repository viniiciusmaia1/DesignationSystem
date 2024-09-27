package com.br.ativatelecom.designationSystem.others;

import com.br.ativatelecom.designationSystem.enuns.StatusEnum;
import lombok.Data;

@Data
public class UpdateStatusRequest {
    private StatusEnum status;
}