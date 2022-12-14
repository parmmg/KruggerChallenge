package com.krugger.challenge.presentation.presenter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RolePresenter {
    private UUID id;
    private String name;
    @Builder.Default
    private List<PermissionPresenter> permissionPresenters = new ArrayList<>();
}
