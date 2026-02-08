
package br.com.uvmarker.makerhub_web.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusUser {
    ACTIVE,
    INACTIVE,
    CHANGE_PASSWORD,
    BLOCKED
}
