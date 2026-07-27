#!/bin/bash

# Determine the appropriate Docker repository for the current Ubuntu version

set -e

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
NC='\033[0m' # No Color

# Check if the system is Ubuntu (case-insensitive)
if ! grep -qi "ubuntu" /etc/os-release; then
    echo -e "${RED}Error: This script is intended only for Ubuntu systems.${NC}"
    exit 1
fi

# Get the release codename (e.g., jammy, focal)
CODENAME=$(lsb_release -cs)

# Ensure codename is not empty
if [ -z "$CODENAME" ]; then
    echo -e "${RED}Failed to determine Ubuntu codename. Please ensure lsb-release is installed.${NC}"
    exit 1
fi

# Determine architecture (default amd64; override with ARCH environment variable)
ARCH="${ARCH:-amd64}"

# Assemble the repository line
REPO_LINE="deb [arch=${ARCH}] https://download.docker.com/linux/ubuntu ${CODENAME} stable"

# Print the result
echo -e "${GREEN}Docker repository for Ubuntu ${CODENAME}:${NC}"
echo "$REPO_LINE"

# Additional output: codename only (useful for playbooks)
echo -e "\n${GREEN}Codename (for use in playbook):${NC} ${CODENAME}"