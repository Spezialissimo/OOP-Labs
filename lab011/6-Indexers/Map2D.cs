using System.Security.AccessControl;

namespace Indexers
{
    using System;
    using System.Collections.Generic;
    using System.Diagnostics.CodeAnalysis;
    using System.Linq;

    /// <inheritdoc cref="IMap2D{TKey1,TKey2,TValue}" />
    public class Map2D<TKey1, TKey2, TValue> : IMap2D<TKey1, TKey2, TValue>
    {
        private readonly Dictionary<Tuple<TKey1, TKey2>, TValue> map = new Dictionary<Tuple<TKey1, TKey2>, TValue>();

        /// <inheritdoc cref="IMap2D{TKey1, TKey2, TValue}.NumberOfElements" />
        public int NumberOfElements { get => this.map.Count; }

        /// <inheritdoc cref="IMap2D{TKey1, TKey2, TValue}.this" />
        public TValue this[TKey1 key1, TKey2 key2]
        {
            get => this.map[Tuple.Create(key1,key2)];
            set => this.map[Tuple.Create(key1,key2)] = value;
        }

        /// <inheritdoc cref="IMap2D{TKey1, TKey2, TValue}.GetRow(TKey1)" />
        public IList<Tuple<TKey2, TValue>> GetRow(TKey1 key1)
        {
            IList<Tuple<TKey2, TValue>> row = new List<Tuple<TKey2, TValue>>();
            foreach (var elem in map)
            {
                if (elem.Key.Item1.Equals(key1))
                {
                    row.Add(Tuple.Create<TKey2, TValue>(elem.Key.Item2, elem.Value));
                }
            }
            
            return row;
        }

        /// <inheritdoc cref="IMap2D{TKey1, TKey2, TValue}.GetColumn(TKey2)" />
        public IList<Tuple<TKey1, TValue>> GetColumn(TKey2 key2)
        {
            IList<Tuple<TKey1, TValue>> column = new List<Tuple<TKey1, TValue>>();
            foreach (var elem in map)
            {
                if (elem.Key.Item1.Equals(key2))
                {
                    column.Add(Tuple.Create<TKey1, TValue>(elem.Key.Item1, elem.Value));
                }
            }
            
            return column;
        }

        /// <inheritdoc cref="IMap2D{TKey1, TKey2, TValue}.GetElements" />
        public IList<Tuple<TKey1, TKey2, TValue>> GetElements()
        {
            IList<Tuple<TKey1, TKey2, TValue>> elementList = new List<Tuple<TKey1, TKey2, TValue>>();
            foreach (var elementOfMap in map)
            {
                TKey1 key1 = elementOfMap.Key.Item1;
                TKey2 key2 = elementOfMap.Key.Item2;
                TValue value = elementOfMap.Value;
                elementList.Add(Tuple.Create(key1, key2, value));
            }

            return elementList;
        }

        /// <inheritdoc cref="IMap2D{TKey1, TKey2, TValue}.Fill(IEnumerable{TKey1}, IEnumerable{TKey2}, Func{TKey1, TKey2, TValue})" />
        public void Fill(IEnumerable<TKey1> keys1, IEnumerable<TKey2> keys2, Func<TKey1, TKey2, TValue> generator)
        {
            foreach (var key1 in keys1)
            {
                foreach (var key2 in keys2)
                {
                    this[key1, key2] = generator.Invoke(key1, key2);
                }
            }
        }

        /// <inheritdoc cref="IEquatable{T}.Equals(T)" />
        public bool Equals(IMap2D<TKey1, TKey2, TValue> other)
        {
            if(other == null)
            {
                throw new ArgumentNullException();
            }

            return map.All(elem =>
                other[elem.Key.Item1, elem.Key.Item2] != null && elem.Equals(other[elem.Key.Item1, elem.Key.Item2]));
        }

        /// <inheritdoc cref="object.Equals(object?)" />
        public override bool Equals(object obj)
        {
            return obj is IMap2D<TKey1, TKey2, TValue> && Equals(obj as IMap2D<TKey1, TKey2, TValue>);
        }

        /// <inheritdoc cref="object.GetHashCode"/>
        public override int GetHashCode()
        {
            return HashCode.Combine(GetElements());
        }

        /// <inheritdoc cref="IMap2D{TKey1, TKey2, TValue}.ToString"/>
        public override string ToString()
        {
            string toString = String.Empty;
            foreach (var keyValuePair in map)
            {
                toString += keyValuePair.Value.ToString();
            }

            return toString;
        }
    }
}
