{
  description = "No Trample Farmland flake";
  inputs = {
    nixpkgs.url = "github:NixOS/nixpkgs/nixos-25.11";
    flake-utils.url = "github:numtide/flake-utils";
  };

  outputs =
    {
      self,
      nixpkgs,
      flake-utils,
      ...
    }:
    flake-utils.lib.eachDefaultSystem (
      system:
      let
        pkgs = import nixpkgs {
          inherit system;
          config.allowUnfree = true;
        };
        java = pkgs.jdk21_headless;
      in
      {
        devShells.default = pkgs.mkShell {
          name = "spring boot + kotlin dev";
          buildInputs = [
            java
          ];
          shellHook = ''
            export JAVA_HOME=$(dirname $(dirname $(which java)))
          '';
        };
      }
    );
}
