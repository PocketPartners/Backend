package fairfinance.pocketpartners.backend.operations.domain.model.queries;

public record GetAllPaymentsByUserIdAndStatusQuery(Long userInformationId, Long status) {
}
