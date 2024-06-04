package fairfinance.pocketpartners.backend.operations.interfaces.rest;

import fairfinance.pocketpartners.backend.operations.domain.model.queries.GetAllExpensesQuery;
import fairfinance.pocketpartners.backend.operations.domain.model.queries.GetExpenseByIdQuery;
import fairfinance.pocketpartners.backend.operations.domain.model.queries.GetExpenseByNameAndUserId;
import fairfinance.pocketpartners.backend.operations.domain.model.valueobjects.ExpenseName;
import fairfinance.pocketpartners.backend.operations.domain.services.ExpenseCommandService;
import fairfinance.pocketpartners.backend.operations.domain.services.ExpenseQueryService;
import fairfinance.pocketpartners.backend.operations.interfaces.rest.resources.CreateExpenseResource;
import fairfinance.pocketpartners.backend.operations.interfaces.rest.resources.ExpenseResource;
import fairfinance.pocketpartners.backend.operations.interfaces.rest.resources.UpdateExpenseResource;
import fairfinance.pocketpartners.backend.operations.interfaces.rest.transform.CreateExpenseCommandFromResourceAssembler;
import fairfinance.pocketpartners.backend.operations.interfaces.rest.transform.ExpenseResourceFromEntityAssembler;
import fairfinance.pocketpartners.backend.operations.interfaces.rest.transform.UpdateExpenseCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/v1/expenses", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name="Expenses", description = "Expenses Management Endpoints")
public class ExpensesController {

    private final ExpenseQueryService expenseQueryService;
    private final ExpenseCommandService expenseCommandService;

    public ExpensesController(ExpenseQueryService expenseQueryService, ExpenseCommandService expenseCommandService) {
        this.expenseQueryService = expenseQueryService;
        this.expenseCommandService = expenseCommandService;
    }

    @PostMapping
    public ResponseEntity<ExpenseResource> createExpense(@RequestBody CreateExpenseResource resource) {
        var createExpenseCommand = CreateExpenseCommandFromResourceAssembler.toCommandFromResource(resource);
        var expenseId = expenseCommandService.handle(createExpenseCommand);
        var getExpenseByNameAndUserId = new GetExpenseByNameAndUserId(new ExpenseName(resource.name()), resource.userId());
        var expense = expenseQueryService.handle(getExpenseByNameAndUserId);
        if (expense.isEmpty()) return ResponseEntity.badRequest().build();
        var expenseResource = ExpenseResourceFromEntityAssembler.toResourceFromEntity(expense.get());
        return new ResponseEntity<>(expenseResource, HttpStatus.CREATED);
    }

    @GetMapping("/{expenseId}")
    public ResponseEntity<ExpenseResource> getExpenseById(@PathVariable Long expenseId) {
        var getExpenseByIdQuery = new GetExpenseByIdQuery(expenseId);
        var expense = expenseQueryService.handle(getExpenseByIdQuery);
        if (expense.isEmpty()) return ResponseEntity.badRequest().build();
        var expenseResource = ExpenseResourceFromEntityAssembler.toResourceFromEntity(expense.get());
        return ResponseEntity.ok(expenseResource);
    }

    @GetMapping
    public ResponseEntity<List<ExpenseResource>> getAllExpenses() {
        var getAllExpensesQuery = new GetAllExpensesQuery();
        var expenses = expenseQueryService.handle(getAllExpensesQuery);
        var expensesResources = expenses.stream().map(ExpenseResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(expensesResources);
    }

    @PutMapping("/{expenseId}")
    public ResponseEntity<ExpenseResource> updateExpense(@PathVariable Long expenseId, @RequestBody UpdateExpenseResource updateExpenseResource) {
        var updateExpenseCommand = UpdateExpenseCommandFromResourceAssembler.toCommandFromResource(expenseId, updateExpenseResource);
        var updatedExpense = expenseCommandService.handle(updateExpenseCommand);
        if(updatedExpense.isEmpty()) return ResponseEntity.badRequest().build();
        var expenseResource = ExpenseResourceFromEntityAssembler.toResourceFromEntity(updatedExpense.get());
        return ResponseEntity.ok(expenseResource);
    }

}
