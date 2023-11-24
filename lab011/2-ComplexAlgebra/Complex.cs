using System;
using System.Runtime.CompilerServices;

namespace ComplexAlgebra
{
    /// <summary>
    /// A type for representing Complex numbers.
    /// </summary>
    ///
    /// TODO: Model Complex numbers in an object-oriented way and implement this class.
    /// TODO: In other words, you must provide a means for:
    /// TODO: * instantiating complex numbers
    /// TODO: * accessing a complex number's real, and imaginary parts
    /// TODO: * accessing a complex number's modulus, and phase
    /// TODO: * complementing a complex number
    /// TODO: * summing up or subtracting two complex numbers
    /// TODO: * representing a complex number as a string or the form Re +/- iIm
    /// TODO:     - e.g. via the ToString() method
    /// TODO: * checking whether two complex numbers are equal or not
    /// TODO:     - e.g. via the Equals(object) method
    public class Complex
    {
        private readonly double _re;
        private readonly double _im;
        
        public double Real
        {
            get => this._re;
        }

        public double Imaginary
        {
            get => this._im;
        }

        public double Modulus
        {
            get => Math.Sqrt(Math.Pow(this._im, 2) + Math.Pow(this._re, 2));
        }
        
        public double Phase
        {
            get => Math.Atan2(Imaginary, Real);
        }

        public Complex(double real, double imaginary)
        {
            this._re = real;
            this._im = imaginary;
        }

        public Complex Plus(Complex toAdd) => new Complex(this._re + toAdd.Real, this._im + toAdd.Imaginary);
        
        public Complex Minus(Complex toAdd) => new Complex(this._re - toAdd.Real, this._im - toAdd.Imaginary);
        
        public Complex Complement() => new Complex(this._re, -this._im);

        public override bool Equals(object obj)
        {
            if (obj is Complex)
            {
                Complex second = obj as Complex;
                return second._im.Equals(this._im) && second._re.Equals(this._re);
            }

            return false;
        }

        public override int GetHashCode() => HashCode.Combine(this._re, this._im);

        //todo migliorare
        public override string ToString() =>
            this._im == 0 ? $"{this._re}" : this._re == 0 ? $"i{this._im}" : $"{this._re}+i{this._im}";
    }
}