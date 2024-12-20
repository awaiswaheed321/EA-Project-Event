package edu.miu.cs544.awais.EventManagementService.security;

import edu.miu.cs544.awais.EventManagementService.exception.custom.UnauthorizedAccessException;
import edu.miu.cs544.awais.EventManagementService.shared.UserRole;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public final class SecurityUtils {
    private SecurityUtils() {
    }

    public static String getPrincipalUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
            return userDetails.getUsername();
        }
        return "Anonymous";
    }

    public static Long getPrincipalId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserPrincipal accountDetails) {
            return accountDetails.getId();
        }
        return null;
    }

    public static String getPrincipalRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserPrincipal accountDetails) {
            return accountDetails.getRole();
        }
        return null;
    }

    //    Customer can only view/update their data
    public static void verifyCustomer(Long emId) {
        String principalRole = getPrincipalRole();
        Long principalId = getPrincipalId();
        if (principalRole == null || principalId == null ||
                (UserRole.CUSTOMER.name().equals(principalRole) && !principalId.equals(emId))) {
            throw new UnauthorizedAccessException("Customer can only access their own resources.");
        }
    }
}
