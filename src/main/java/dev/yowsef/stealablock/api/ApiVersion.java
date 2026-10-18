package dev.yowsef.stealablock.api;

import org.jetbrains.annotations.NotNull;

/**
 * Semantic version of the StealABlock Developer API.
 */
public record ApiVersion(int major, int minor, int patch) implements Comparable<ApiVersion> {

    public static final ApiVersion CURRENT = new ApiVersion(1, 3, 0);

    public boolean isAtLeast(int reqMajor, int reqMinor) {
        return isAtLeast(reqMajor, reqMinor, 0);
    }

    public boolean isAtLeast(int reqMajor, int reqMinor, int reqPatch) {
        if (this.major != reqMajor) return this.major > reqMajor;
        if (this.minor != reqMinor) return this.minor > reqMinor;
        return this.patch >= reqPatch;
    }

    public boolean isCompatibleWith(@NotNull ApiVersion required) {
        return this.major == required.major && isAtLeast(required.major, required.minor, required.patch);
    }

    @Override
    public int compareTo(@NotNull ApiVersion other) {
        if (this.major != other.major) return Integer.compare(this.major, other.major);
        if (this.minor != other.minor) return Integer.compare(this.minor, other.minor);
        return Integer.compare(this.patch, other.patch);
    }

    @Override
    public @NotNull String toString() {
        return major + "." + minor + "." + patch;
    }
}
