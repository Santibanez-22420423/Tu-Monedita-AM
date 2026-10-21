# Git Workflow

## Main branches

### main

Contains stable project versions.

### develop

Contains integrated development changes.

## Working branches

Create new working branches from `develop`.

Examples:
 - `feature/authentication`
 - `feature/transactions`
 - `fix/login-validation`
 - `chore/project-architecture`

## Workflow

1. Switch to `develop`.
2. Pull the latest changes.
3. Create a working branch.
4. Make and commit changes.
5. Push the working branch.
6. Open a pull request to `develop`.
7. Request at least one approval.
8. Merge after review.

## Conventional Commits

Format:

`type(scope): description`

Examples:

- `feat(auth): add login screen`
- `fix(transactions): correct amount validation`
- `refactor(dashboard): simplify state management`
- `docs: update architecture guide`
- `chore: organize project structure`

## Merge strategy

Use squash merging for feature branches.

Changes from `develop` to `main` require a reviewed pull request.
