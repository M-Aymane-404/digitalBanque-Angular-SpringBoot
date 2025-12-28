

## 🔐 Authentication & Authorization

This mini-project implements **role-based access control (RBAC)** with two predefined users.

### 👥 Test Users

| Username | Password | Role  |
| -------- | -------- | ----- |
| admin    | 1234     | ADMIN |
| user1    | 1234     | USER  |

---

### 🧑‍💼 ADMIN Permissions

The **ADMIN** user has full access to the system:

* Create clients
* Update clients
* Delete clients
* Create bank accounts
* Update bank accounts
* Delete bank accounts
* Activate / Suspend accounts
* Perform all transactions:

  * Virement
  * Retrait
  * Versement
* View all transactions and accounts

---

### 👤 USER Permissions

The **USER** role has limited access:

* View clients
* View bank accounts
* Perform transactions:

  * Virement
  * Retrait
  * Versement
* View transactions history

🚫 **Restrictions for USER**:

* Cannot delete clients
* Cannot update clients
* Cannot delete accounts
* Cannot update accounts
* Cannot activate or suspend accounts

---

