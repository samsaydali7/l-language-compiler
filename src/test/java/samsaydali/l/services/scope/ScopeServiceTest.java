package samsaydali.l.services.scope;

import org.junit.jupiter.api.Test;
import samsaydali.l.ast.common.Identifier;
import samsaydali.l.ast.common.Type;

import static org.junit.jupiter.api.Assertions.*;

class ScopeServiceTest {

    Identifier x1 = new Identifier("x", new Type("String"));
    Identifier x2 = new Identifier("x", new Type("Int"));
    Identifier y1 = new Identifier("y", new Type("String"));

    @Test
    public void canReadIdentifierFromScope() {
        ScopeService scopeService = new ScopeService();

        scopeService.addIdentifier(x1);

        Identifier result = scopeService.getIdentifier("x");

        assertAll(() -> {
            assertEquals(result.getName(), x1.getName());
            assertEquals(result.getType(), x1.getType());
        });

        assertThrows(IdentifierNotFoundInScope.class, () -> {
            scopeService.getIdentifier("y");
        });
    }

    @Test
    public void throwsErrorForDuplication() {
        ScopeService scopeService = new ScopeService();

        assertThrows(IdentifierAlreadyInScope.class, () -> {
            scopeService.addIdentifier(x1);
            scopeService.addIdentifier(x2);
        });
    }

    @Test
    public void addsToInnerScope() {
        ScopeService scopeService = new ScopeService();

        assertAll(() -> {
            scopeService.addIdentifier(x1);
            Identifier result1 = scopeService.getIdentifier("x");
            assertEquals(result1.getType(), x1.getType());

            scopeService.openScope();
            scopeService.addIdentifier(x2);
            scopeService.addIdentifier(y1);
            Identifier result2 = scopeService.getIdentifier("x");
            Identifier result3 = scopeService.getIdentifier("y");
            assertEquals(result2.getType(), x2.getType());
            assertEquals(result3.getType(), y1.getType());


            scopeService.closeScope();
            Identifier result4 = scopeService.getIdentifier("x");
            assertEquals(result4.getType(), x1.getType());
            assertThrows(IdentifierNotFoundInScope.class, () -> {
                scopeService.getIdentifier("y");
            });
        });
    }
}