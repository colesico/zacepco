#!/bin/bash

# Generate an SSH key pair for the user zacepco

set -e

# Configuration
KEY_COMMENT="zacepco@$(hostname)"
KEY_DIR="$HOME/.ssh"
KEY_FILE="$KEY_DIR/id_ed25519_zacepco"

# Create .ssh directory if it doesn't exist
mkdir -p "$KEY_DIR"
chmod 700 "$KEY_DIR"

# Check if key already exists
if [ -f "$KEY_FILE" ]; then
    read -p "Key $KEY_FILE already exists. Overwrite? (y/N) " -n 1 -r
    echo
    if [[ ! $REPLY =~ ^[Yy]$ ]]; then
        echo "Aborted."
        exit 0
    fi
fi

# Generate the key (no passphrase)
ssh-keygen -t ed25519 -f "$KEY_FILE" -C "$KEY_COMMENT" -N ""

# Set proper permissions
chmod 600 "$KEY_FILE"
chmod 644 "${KEY_FILE}.pub"

# Output results
echo "=========================================="
echo "✅ Keys generated:"
echo "   Private key: $KEY_FILE"
echo "   Public key:  ${KEY_FILE}.pub"
echo
echo "📋 Public key (copy this into the target_ssh_key variable of your playbook):"
echo "=========================================="
cat "${KEY_FILE}.pub"
echo "=========================================="