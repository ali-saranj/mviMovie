package your.kasra.today.utils

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    /**
     * Represents a successful result with data.
     *
     * @param data The data returned on success.
     */
    class Success<T>(data: T?): Resource<T>(data)

    /**
     * Represents a failed result with an error message.
     *
     * @param message The error message indicating the reason for failure.
     * @param data Optional data that might be available even in case of an error.
     */
    class Error<T>(message: String, data: T? = null): Resource<T>(data, message)
}