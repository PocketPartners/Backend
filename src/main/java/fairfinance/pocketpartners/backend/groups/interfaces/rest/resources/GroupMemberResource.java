package fairfinance.pocketpartners.backend.groups.interfaces.rest.resources;

import fairfinance.pocketpartners.backend.groups.domain.model.entities.GroupMember;

public record GroupMemberResource (Long groupId, Long userId, String name, String fullName, String email, String phoneNumber, java.util.Date joinedAt){
    public static Object fromCommandToResource(GroupMember groupMember) {
        return new GroupMemberResource(groupMember.getGroup().getId(), groupMember.getUser().getUserInformation().getId(), groupMember.getGroup().getName(), groupMember.getUser().getUserInformation().getFullName(), groupMember.getUser().getUserInformation().getEmailAddress(), groupMember.getUser().getUserInformation().getPhoneNumber(), groupMember.getJoinedAt());
    }
}
